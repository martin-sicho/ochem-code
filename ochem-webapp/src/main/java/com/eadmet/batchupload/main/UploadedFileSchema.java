/* Copyright (C) 2022 BIGCHEM GmbH <info@bigchem.de>
 *
 * Contact: info@bigchem.de
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License (AGPL)
 * as published by the Free Software Foundation; either version 3.0
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the Affero GNU General Public License for more details.
 *
 * You should have received a copy of the Affero GNU Lesser General Public License
 * along with this program; If not, see <https://www.gnu.org/licenses/>. 
 */

package com.eadmet.batchupload.main;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="file")
public class UploadedFileSchema extends ArrayList<UploadedSheetSchema> 
{
	private static final long serialVersionUID = 1L;
	
	public String md5;
	
	@XmlTransient
	private Integer selectedSheet = 0;
	
	@XmlAttribute
	public Integer getSelectedSheet()
	{
		return selectedSheet;
	}
	
	public void setSelectedSheet(Integer sheet)
	{
		selectedSheet = sheet;
		
		for (UploadedSheetSchema s : this)
			s.ignore = true;
		
		get(sheet).ignore = false;
	}
	
	public UploadedSheetSchema getSelectedSheetSchema()
	{
		return get(selectedSheet);
	}
	
	public UploadedFileSchema()
	{
		
	}
	
	@XmlElement
	public List<UploadedSheetSchema> getSheets() 
	{
		return this;
	}
}