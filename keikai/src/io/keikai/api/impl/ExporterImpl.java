/* ExporterImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/5/1 , Created by dennis
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package io.keikai.api.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import io.keikai.api.AreaRef;
import io.keikai.api.Exporter;
import io.keikai.model.SheetRegion;
import io.keikai.range.SExporter;
import io.keikai.api.model.Book;
import io.keikai.api.model.Sheet;
import io.keikai.api.model.impl.BookImpl;
import io.keikai.api.model.impl.SheetImpl;

/**
 * 
 * @author dennis
 * @since 3.0.0
 */
public class ExporterImpl implements Exporter, Serializable {
	private static final long serialVersionUID = 7397284261685721810L;
	
	private SExporter _exporter;
	public ExporterImpl(SExporter exporter){
		if(exporter==null){
			throw new IllegalAccessError("exporter not found");
		}
		
		this._exporter =exporter;
	}
	public void export(Book book, OutputStream fos) throws IOException{
		_exporter.export(((BookImpl)book).getNative(), fos);
	}
	public void export(Book book, File file) throws IOException{
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			_exporter.export(((BookImpl)book).getNative(), fos);
		}finally{
			if(fos!=null){
				try{
					fos.close();
				}catch(Exception x){}//eat
			}
		}
	}
	@Deprecated
	public void export(Sheet sheet, OutputStream fos) throws IOException{
		_exporter.export(((SheetImpl)sheet).getNative(), fos);
	}
	@Deprecated
	public void export(Sheet sheet, AreaRef selection,OutputStream fos) throws IOException{
		_exporter.export(new SheetRegion(((SheetImpl) sheet).getNative(),
				selection.getRow(), selection.getColumn(), selection.getLastRow(), selection.getLastColumn()), fos);
	}
//	@Override
//	public boolean isSupportHeadings() {
//		return exporter instanceof Headings;
//	}
//	@Override
//	public void enableHeadings(boolean enable) {
//		if(isSupportHeadings()){
//			((Headings)exporter).enableHeadings(enable);
//		}else{
//			throw new RuntimeException("this export doesn't support headings");
//		}
//	}
}
