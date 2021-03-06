/*
{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2014/8/5, Created by henri
}}IS_NOTE

Copyright (C) 2014 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/

package io.keikai.api.model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.keikai.api.model.Book;
import io.keikai.api.model.Font;
import io.keikai.api.model.RichText;
import io.keikai.model.SFont;
import io.keikai.model.SRichText;
import io.keikai.model.util.Validations;

/**
 * @author henri
 *
 */
public class RichTextImpl implements RichText, Serializable {
	private static final long serialVersionUID = 4459139353293789714L;
	
	final Book _book;
	final SRichText _native;
	
	public RichTextImpl(Book book) {
		_book = book;
		_native = new io.keikai.model.impl.RichTextImpl();
	}
	
	public SRichText getNative() {
		return _native;
	}
	
	@Override
	public String getText() {
		
		return _native.getText();
	}

	@Override
	public Font getFont() {
		final SFont font0 = _native.getFont();
		return font0 == null ? null :
			new FontImpl(((BookImpl) _book).getRef(), new SimpleRef<SFont>(font0));
	}

	@Override
	public List<Segment> getSegments() {
		List<SRichText.Segment> modelsegs = _native.getSegments();
		if (modelsegs.isEmpty()) {
			return Collections.emptyList();
		}
		List<Segment> apisegs = new ArrayList<Segment>(modelsegs.size());
		for (SRichText.Segment seg : modelsegs) {
			final SFont sfont = seg.getFont();
			final Font font = sfont == null ? //ZSS-1138
					null : new FontImpl(((BookImpl) _book).getRef(), new SimpleRef<SFont>(sfont));
			apisegs.add(new SegmentImpl(seg.getText(), font));
		}
		return apisegs;
	}

	@Override
	public void addSegment(String text, Font font) {
		Validations.argNotNull(text);
		if("".equals(text)) return;
		_native.addSegment(text, ((FontImpl)font).getNative());
	}

	@Override
	public void clearSegments() {
		_native.clearSegments();
	}
}
