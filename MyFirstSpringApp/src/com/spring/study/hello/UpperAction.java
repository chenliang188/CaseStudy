/**
 * 
 */
package com.spring.study.hello;

/**
 * @author Administrator
 *
 */
public class UpperAction implements Action
{
    private String message;
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String string)
    {
        message = string;
    }
    
    /* (non-Javadoc)
     * @see com.test.Action#execute(java.lang.String)
     */
    @Override
    public String execute(String str)
    {
        // TODO Auto-generated method stub
        return (getMessage() + str).toUpperCase();
    }
    
}