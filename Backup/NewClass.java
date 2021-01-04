/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.io.Serializable;

/**
 *
 * @author alex
 */
public class NewClass implements Serializable {
	
	String str = "";

	public NewClass(String s) {
		str = s;
	}
	
	public String getString() {
		return str;
	}
}