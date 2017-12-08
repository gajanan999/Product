package com.billdiary.utility;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


@Component
@Scope("prototype")
public class IconGallery {
	
	public  FontAwesomeIconView fontAwesomeSaveIconView= new FontAwesomeIconView(FontAwesomeIcon.SAVE);
	public  FontAwesomeIconView fontAwesomeTrashIconView= new FontAwesomeIconView(FontAwesomeIcon.TRASH);
	
	public  FontAwesomeIconView getSaveIcon() {
		fontAwesomeSaveIconView.setSize("1.5em");
		return fontAwesomeSaveIconView;
	}
	public FontAwesomeIconView getDeleteIcon()
	{
		fontAwesomeTrashIconView.setSize("1.5em");
		return fontAwesomeTrashIconView;
	}

}
