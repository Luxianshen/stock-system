/**
 * 
 */
package com.zjht.web.commons.template;

import org.smarabbit.massy.web.menu.AbstractMenuGroup;
import org.smarabbit.massy.web.menu.Visitor;

/**
 * @author huangkaihui
 *
 */
public class PageSidebarRootMenu extends AbstractMenuGroup {

	public static final String NAME = "org.smarabbit.web.menu.PageSideBar";
	
	/**
	 * 
	 */
	public PageSidebarRootMenu() {
		this.setName(NAME);
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.AbstractMenuGroup#doRender(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int, java.lang.String)
	 */
	@Override
	protected void doRender(StringBuffer buffer, Visitor visitor,
			int indentNumber, String superiorMenuName) {
		this.renderNewLine(buffer);
		String htmlText = this.renderItems(visitor, indentNumber);
		if (htmlText != null){
			buffer.append(htmlText);
		}
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.AbstractMenuGroup#beginRenderItems(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void beginRenderItems(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		
	}
}
