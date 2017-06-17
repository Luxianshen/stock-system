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
public class UserMenuGroup extends AbstractMenuGroup {
	
	public static final String NAME = "org.smarabbit.web.menu.User";
	
	/**
	 * 
	 */
	public UserMenuGroup() {
		this.setName(NAME);
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRender(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void beginRender(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		buffer.append("<li class=\"dropdown user\">");
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRenderHref(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor)
	 */
	@Override
	protected boolean beginRenderHref(StringBuffer buffer, Visitor visitor, int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		buffer.append("<a href=\"").append("#")
				.append("\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"")
				.append(" data-hover=\"dropdown\" data-close-others=\"true\">");
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#endRenderHref(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void endRenderHref(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		super.endRenderHref(buffer, visitor, indentNumber);
	}
	
	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#beginRenderIcon(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor)
	 */
	@Override
	protected boolean beginRenderIcon(StringBuffer buffer, Visitor visitor, int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		
		buffer.append("<img alt=\"\" src=\"")
				.append(visitor.getRequest().getContextPath())
				.append("/metronic/img/avatar1_small.jpg\" />");
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#endRenderIcon(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor)
	 */
	@Override
	protected void endRenderIcon(StringBuffer buffer, Visitor visitor, int indentNumber) {
		//无需处理
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#renderLabel(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void renderLabel(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		buffer.append("<span class=\"username\">")
			.append(visitor.getCurrentUser().getName())
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
		
		buffer.append("<i class=\"icon-angle-down\">");
		return true;
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.SimpleMenu#endRenderExtend(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void endRenderExtend(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		buffer.append("</i>");
	}

	/* (non-Javadoc)
	 * @see org.smarabbit.massy.web.menu.AbstractMenuGroup#beginRenderItems(java.lang.StringBuffer, org.smarabbit.massy.web.menu.Visitor, int)
	 */
	@Override
	protected void beginRenderItems(StringBuffer buffer, Visitor visitor,
			int indentNumber) {
		this.renderNewLine(buffer);
		this.renderIndent(buffer, indentNumber);
		buffer.append("<ul class=\"dropdown-menu\" >");
	}
}
