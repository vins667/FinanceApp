/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class AgentBean {
    
      private String agentName;
    private String agentAdd;
    private String agentCode;
    private String vendId;
    
    public AgentBean(String agentName, String agentAdd,String agentCode,String vendId) {
		super();
		this.agentName = agentName;
		this.agentAdd = agentAdd;
		this.agentCode = agentCode;
                this.vendId=vendId;
	}
	public AgentBean() {
		super();
	}

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentAdd() {
        return agentAdd;
    }

    public void setAgentAdd(String agentAdd) {
        this.agentAdd = agentAdd;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getVendId() {
        return vendId;
    }

    public void setVendId(String vendId) {
        this.vendId = vendId;
    }
   
    
}
