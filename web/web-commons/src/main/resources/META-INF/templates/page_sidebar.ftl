        
        <!-- BEGIN SIDEBAR -->
        <div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul id="sidebar" class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li>
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<form class="sidebar-search" action="extra_search.html"
						method="POST">
						<div class="form-container">
							<div class="input-box">
								<a href="javascript:;" class="remove"></a> <input type="text"
									placeholder="Search..." /> <input type="button" class="submit"
									value=" " />
							</div>
						</div>
					</form> <!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				<li class="start active ">
					<a href="index.html">
						<i class="icon-home"></i>
						<span class="title">Dashboard</span>
						<span class="selected"></span>
					</a>
				</li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-bookmark-empty"></i>
                        <span class="title">字典管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${request.contextPath}/dictionary/list.html">
                                字典列表</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-bookmark-empty"></i>
                        <span class="title">物料管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${request.contextPath}/category/list.html">
                                物料分类</a>
                        </li>
                        <li>
                            <a href="${request.contextPath}/material/list.html">
                                物料列表</a>
                        </li>
                        </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-bookmark-empty"></i>
                        <span class="title">库存管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${request.contextPath}/apply/list.html">
                                申请列表</a>
                        </li>
                        <li>
                            <a href="${request.contextPath}/inventory/list.html">
                                库存明细</a>
                        </li>

                    </ul>
                </li>
				${pageSidebarMenu}
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>