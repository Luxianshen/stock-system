/**
 * 
 */
package com.zjht.web.commons.template;

import org.smarabbit.massy.web.menu.Visitor;

/**
 * @author huangkaihui
 *
 */
public class InboxMenu extends UserMenu {

	/**
	 * 
	 */
	public InboxMenu() {
		super();
		
		this.setIcon("icon-envelope");
		this.setLabel("收件箱");
		this.setName("org.smarabbit.web.menu.User.Inbox");
		this.setHref("inbox.html");
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRenderExtend(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected boolean beginRenderExtend(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		int number =3;
		buffer.append("<span class=\"badge badge-danger\">")
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
