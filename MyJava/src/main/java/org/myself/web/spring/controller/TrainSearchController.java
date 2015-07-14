package org.myself.web.spring.controller;

import org.myself.web.spring.utils.https.MyX509TrustManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-26
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TrainSearchController {

}
