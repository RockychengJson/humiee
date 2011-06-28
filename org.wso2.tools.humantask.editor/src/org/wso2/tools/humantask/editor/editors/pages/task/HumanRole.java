package org.wso2.tools.humantask.editor.editors.pages.task;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGenericHumanRole;

public class HumanRole {

	private TGenericHumanRole genericHumanRole;
	private String type;
	
	public HumanRole(TGenericHumanRole genericHumanRole,String type)
	{
		this.genericHumanRole=genericHumanRole;
		this.type=type;
	}
	
	public void setGenericHumanRole(TGenericHumanRole genericHumanRole)
	{
		this.genericHumanRole=genericHumanRole;
	}
	public void setType(String type)
	{
		this.type=type;
	}
	public TGenericHumanRole getGenericHumanRole()
	{
		return genericHumanRole;
	}
	public String getType()
	{
		return type;
	}
	public String getLogicalPeopleGroup()
	{
		if(genericHumanRole.getFrom().getLogicalPeopleGroup()!=null)
		{
			return genericHumanRole.getFrom().getLogicalPeopleGroup().toString();
		}
		else
		{		
		return "";
		}
	}
	
	public String getPeopleAssignMethodology()
	{
		String s=null;
		if(genericHumanRole.getFrom().getLogicalPeopleGroup()!=null)
		{
			s= "By LogicalPeopleGroup";
		}
		
		if(genericHumanRole.getFrom().getLiteral()!=null)
		{
			s= "By Literal";
		}
		if(genericHumanRole.getFrom().getExpressionLanguage()!=null)
		{
			s= "By ExpressionLanguage";
		}
		return s;
		
		
	}
	
}
