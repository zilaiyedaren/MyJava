package org.myself.web.spring.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.myself.web.spring.serviceimpl.UsersServiceImpl;
import org.myself.web.spring.vo.TUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.TextEscapeUtils;

/**
 * 带验证码校验功能的用户名、密码认证过滤器
 * 支持不输入验证码；支持验证码忽略大小写。
 */
public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;
    private boolean allowEmptyValidateCode = false;
    private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
    private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;
    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
    // session中保存的验证码
    public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "rand";
    // 输入的验证码
    public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code";

    @Autowired
    private UsersServiceImpl usersService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: "
                            + request.getMethod());
        }
//        //检验验证码
//        if (!isAllowEmptyValidateCode())
//            checkValidateCode(request);

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);

    }

    /**
     *
     * 比较session中的验证码和用户输入的验证码是否相等
     *
     */
    protected void checkValidateCode(HttpServletRequest request) {
        String sessionValidateCode = obtainSessionValidateCode(request);
        String validateCodeParameter = obtainValidateCodeParameter(request);
        if (StringUtils.isEmpty(validateCodeParameter)
                || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            throw new AuthenticationServiceException("验证码错误！");
        }
    }

    private String obtainValidateCodeParameter(HttpServletRequest request) {
        return request.getParameter(validateCodeParameter);
    }

    protected String obtainSessionValidateCode(HttpServletRequest request) {
        Object obj = request.getSession()
                .getAttribute(sessionvalidateCodeField);
        return null == obj ? "" : obj.toString();
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getValidateCodeName() {
        return sessionvalidateCodeField;
    }

    public void setValidateCodeName(String validateCodeName) {
        this.sessionvalidateCodeField = validateCodeName;
    }

    public boolean isAllowEmptyValidateCode() {
        return allowEmptyValidateCode;
    }

    public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
        this.allowEmptyValidateCode = allowEmptyValidateCode;
    }

}
