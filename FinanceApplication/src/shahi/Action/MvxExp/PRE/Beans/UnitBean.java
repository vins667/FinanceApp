/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

 
/** 
 *
 * @author Ravindra
 */
public class UnitBean {
    
        private String UNIT_CODE;
	private String UNIT_DESC;
	private String UNIT_ADDRESS;
	
	public UnitBean(String uNIT_CODE, String uNIT_DESC,
			String uNIT_ADDRESS) {
		super();
		UNIT_CODE = uNIT_CODE;
		UNIT_DESC = uNIT_DESC;
		UNIT_ADDRESS = uNIT_ADDRESS;
	}
	public UnitBean() {
		super();
	}
	public String getUNIT_CODE() {
		return UNIT_CODE;
	}
	public void setUNIT_CODE(String uNIT_CODE) {
		UNIT_CODE = uNIT_CODE;
	}
	public String getUNIT_DESC() {
		return UNIT_DESC;
	}
	public void setUNIT_DESC(String uNIT_DESC) {
		UNIT_DESC = uNIT_DESC;
	}
	public String getUNIT_ADDRESS() {
		return UNIT_ADDRESS;
	}
	public void setUNIT_ADDRESS(String uNIT_ADDRESS) {
		UNIT_ADDRESS =  uNIT_ADDRESS;
	}
	
}
