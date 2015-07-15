<%--
  Created by IntelliJ IDEA.
  User: wuheng
  Date: 14-10-6
  Time: 下午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>caipiao</title>
    <style>
         .ui-link{
             color:#000;
         }
    </style>
</head>
<body>
      <div data-theme="b" data-role="header" data-position="fixed">
          <h3>caipiao</h3>
          <a data-role="button" href="javascript:history.go(-1);" data-icon="arrow-l" data-iconpos="left" rel="external" class="ui-btn-left" >返回</a>
          <a data-role="button" href="<s:url action="index" namespace="/"/>" data-icon="home" data-iconpos="right" rel="external" class="ui-btn-right">主页</a>
      </div>

         <div style="margin-left:10px;margin-right: 10px;padding-left: 10px;padding-right: 10px;">
        <%--<div data-role="content" data-theme="c">--%>
            <div data-role="collapsible-set" data-theme="b">
                <div data-role="collapsible" data-collapsed="false">
                    <h3>全国</h3>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=ssq&caipiaoName=双色球" rel="external">双色球</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=dlt&caipiaoName=大乐透" rel="external">大乐透</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=fc3d&caipiaoName=福彩3D" rel="external">福彩3D</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=pl3&caipiaoName=排列3" rel="external">排列3</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=pl5&caipiaoName=排列5" rel="external">排列5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=qlc&caipiaoName=七乐彩" rel="external">七乐彩</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=qxc&caipiaoName=七星彩" rel="external">七星彩</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=zcbqc&caipiaoName=六场半全场" rel="external">六场半全场</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=zcjqc&caipiaoName=四场进球彩" rel="external">四场进球彩</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=zcsfc&caipiaoName=十四场胜负彩（任9）" rel="external">十四场胜负彩(任9)</a><br/>
                </div>
                <div data-role="collapsible">
                    <h3>高频</h3>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=hb11x5&caipiaoName=湖北11选5" rel="external">湖北11选5</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=gd11x5&caipiaoName=广东11选5" rel="external">广东11选5</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=js11x5&caipiaoName=江苏11选5" rel="external">江苏11选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=sh11x5&caipiaoName=上海11选5" rel="external">上海11选5</a><br/>
                    <a style="color:#000;" href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=sd11x5&caipiaoName=山东11选5" rel="external">山东11选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=tj11x5&caipiaoName=天津11选5" rel="external">天津11选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=zj11x5&caipiaoName=浙江11选5" rel="external">浙江11选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=hubk3&caipiaoName=湖北快三" rel="external">湖北快三</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=shk3&caipiaoName=上海快三" rel="external">上海快三</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=jxssc&caipiaoName=江西时时彩" rel="external">江西时时彩</a><br/>
                </div>
                <div data-role="collapsible">
                    <h3>地方</h3>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=ahfc25x5&caipiaoName=安徽福彩22选5" rel="external">安徽福彩22选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=bjtc33x7&caipiaoName=北京体彩33选7" rel="external">北京体彩33选7</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=ctfc22x5&caipiaoName=湖北楚天风采22选5" rel="external">湖北楚天风采22选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=df6j1&caipiaoName=七省东方6+1" rel="external">七省东方6</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=fjtc22x5&caipiaoName=福建体彩22选5" rel="external">福建体彩22选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=gdfc26x5&caipiaoName=广东南粤风采26选5" rel="external">广东南粤风采26选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=jstc7ws&caipiaoName=江苏体彩7位数" rel="external">江苏体彩7位数</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=qlfc23x5&caipiaoName=山东齐鲁风采23选5" rel="external">山东齐鲁风采23选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=zjtc20x5&caipiaoName=浙江体彩20选5" rel="external">浙江体彩20选5</a><br/>
                    <a style="color:#000;"href="<s:url action="caipiao_info" namespace="/info" />?caipiaoCode=shttcx4&caipiaoName=上海天天彩选4" rel="external">上海天天彩选4</a><br/>
                </div>
        </div>
    </div>
</body>
</html>