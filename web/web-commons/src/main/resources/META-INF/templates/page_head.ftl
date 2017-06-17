     
     <!-- BEGIN HEADER -->
    <div class="header navbar navbar-inverse navbar-fixed-top">
        <!-- BEGIN TOP NAVIGATION BAR -->
        <div class="header-inner">
            <!-- BEGIN LOGO -->
            <a class="navbar-brand" href="index.html">
                <img src="${request.contextPath}/metronic/img/logo.png" alt="logo" class="img-responsive" />
            </a>
            <!-- END LOGO -->

            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <img src="${request.contextPath}/metronic/img/menu-toggler.png" alt="" />
            </a>
            <!-- END RESPONSIVE MENU TOGGLER -->

            <!-- BEGIN TOP NAVIGATION MENU -->
            <ul class="nav navbar-nav pull-right">
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <img alt="" src="${request.contextPath}/metronic/img/avatar1_small.jpg"/>
                        <span class="username">若晨</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="extra_profile.html"><i class="icon-user"></i> 我的档案</a>
                        </li>
                        <li><a href="page_calendar.html"><i class="icon-calendar"></i> 日程安排</a>
                        </li>
                        <li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox <span class="badge badge-danger">3</span></a>
                        </li>
                        <li><a href="#"><i class="icon-tasks"></i> My Tasks <span class="badge badge-success">7</span></a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="javascript:;" id="trigger_fullscreen"><i class="icon-move"></i> 全屏</a>
                        </li>
                        <li><a href="extra_lock.html"><i class="icon-lock"></i> 锁屏</a>
                        </li>
                        <li><a href="login.html"><i class="icon-key"></i> 注销</a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
            <!-- END TOP NAVIGATION MENU -->
        </div>
    </div>
    <!-- END HEADER -->