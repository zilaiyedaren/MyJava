<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
         <div class="container">
             <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
             </a>
             <a class="brand" href="#">
                 ${name?default("Petstore")}
             </a>

             <div class="nav-collapse">
                 <ul class="nav">
                     <li class="active">
                         <a href="/index.jsp">Home</a>
                     </li>
                     <li><a href="#about">About</a></li>
                     <li><a href="#contact">Contact</a></li>
                     <#if (username)!!="">
                     <li><a href="/logout.jsp">${(username)!}[Logout]</a></li>
                     <#else>
                     <li><a href="/login.jsp">Login</a></li>
                     </#if>
                 </ul>
             </div>

         </div>
    </div>
</div>