/* IndirectRef.java

	Purpose:
		
	Description:
		
	History:
		Nov 21, 2014 11:37:26 AM, Created by henrichen

	Copyright (C) 2014 Potix Corporation. All Rights Reserved.
*/

package io.keikai.model.sys.dependency;

/**
 * Used to handle reference generated by INDIRECT(text) function.
 * 
 * @author henri
 * @since 3.7.0
 */
public interface IndirectRef extends Ref {
	/**
	 * Identify which INDIRECT function in a cell formula. 
	 * @return
	 */
	int getPtgIndex();
}
