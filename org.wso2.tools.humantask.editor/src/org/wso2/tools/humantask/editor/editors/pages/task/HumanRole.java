package org.wso2.tools.humantask.editor.editors.pages.task;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGenericHumanRole;

public class HumanRole {

	private TGenericHumanRole genericHumanRole;
	private String type;
	private int indexOfEList;
	
	
	public HumanRole(TGenericHumanRole genericHumanRole,String type,int index)
	{
		this.genericHumanRole=genericHumanRole;
		this.type=type;
		this.indexOfEList=index;
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
	public int getIndexOfEList()
	{
		return indexOfEList;
	}
	public String getLogicalPeopleGroup()
	{
		if(genericHumanRole.getFrom()!=null)
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
	
	public int getTypeByIndex()
	{
		if("PotentialOwners".equals(getType()))
		{
			return 0;
		}
		else	if("ExcludedOwners".equals(getType()))
		{
				return 1;
		}
		else if("TaskInitiator".equals(getType()))
		{
			return 2;
		}	
	
		else if("TaskStakeholders".equals(getType()))
		{
			return 3;
		}
			
		else if("BusinessAdministrators".equals(getType()))
		{
			return 4;
		}
		else if("Recipients".equals(getType()))
		{
			return 5;
		}
		else{
		return -1;
		}
		
	}
	
}
