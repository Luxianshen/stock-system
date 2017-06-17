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
public class PageSidebarSubMenuGroup extends AbstractMenuGroup {

	/**
	 * 
	 */
	public PageSidebarSubMenuGroup() {
		this.setSuperiorName(PageSidebarRootMenu.NAME);
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRender(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void beginRender(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderIndent(buffer, indentNumber);
		buffer.append("<li class=\"\">");
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#endRenderHref(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void endRenderHref(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		buffer.append("</a>");
	}
	
	

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRenderIcon(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected boolean beginRenderIcon(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		return super.beginRenderIcon(buffer, visitor, indentNumber);
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#renderLabel(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void renderLabel(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		
		buffer.append("<span class=\"title\">")
			.append(this.getLabel())
			.append("</span>");
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRenderExtend(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected boolean beginRenderExtend(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		
		buffer.append("<span class=\"arrow \"></span>");
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.AbstractMenuGroup#beginRenderItems(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void beginRenderItems(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		buffer.append("<ul class=\"sub-menu\">");
	}

}
