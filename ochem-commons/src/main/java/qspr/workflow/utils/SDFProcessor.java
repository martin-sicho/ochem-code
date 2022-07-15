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

package qspr.workflow.utils;

import java.io.IOException;
import java.util.regex.Pattern;

import qspr.dao.Various;

import com.eadmet.utils.OCHEMUtils;

/**
 * A primitivistic pattern-based standartizer of SDF files
 * @author midnighter
 *
 */
public class SDFProcessor
{
	private static Pattern allAfterEnd = Pattern.compile("END.*",Pattern.DOTALL);
	private static Pattern firstThreeLines = Pattern.compile("^[^\\n]*\\n[^\\n]*\\n[^\\n]*\\n",Pattern.DOTALL);

	public static String standartize(String sdf)
	{
		return allAfterEnd.matcher(firstThreeLines.matcher(sdf).replaceAll("1\n2\n3\n")).replaceAll("END");
	}

	public static String getMD5SDF(String sdf, boolean twoD) {

		try{
			sdf = twoD ? Various.molecule.convertToCanonicalSMILES(sdf) : standartize(sdf);
		}catch(IOException e){
		}

		return OCHEMUtils.getMD5(sdf);
	}
}
