/**
 * 
 */
package com.zjht.web.commons.template;

import org.smarabbit.massy.web.menu.Visitor;

/**
 * @author huangkaihui
 *
 */
public class TasksMenu extends UserMenu {

	/**
	 * 
	 */
	public TasksMenu() {
		this.setIcon("icon-tasks");
		this.setLabel("待办事务");
		this.setName("org.smarabbit.web.menu.User.Tasks");
		this.setHref("#");
		this.setDivided(true);
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRenderExtend(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected boolean beginRenderExtend(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		int number =7;
		buffer.append("<span class=\"badge badge-success\">")
			.append(number);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#endRenderExtend(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void endRenderExtend(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		buffer.append("</span>");
	}
}
