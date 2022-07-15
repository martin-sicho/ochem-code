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

package com.eadmet.business;

import java.io.IOException;
import com.eadmet.exceptions.UserFriendlyException;

import qspr.dao.ChemInfEngine;
import qspr.dao.Various;
import qspr.entities.AlertSubstitutionVariable;
import qspr.metaserver.util.ExtendedSMART;
import qspr.workflow.utils.QSPRConstants;

public class SMARTSMatcherCDK implements SmartsMatcher {

	@Override
	public boolean match(String smiles, String smarts) {
		ExtendedSMART smart = SmartsMatcher.getExtended(AlertSubstitutionVariable.substituteVariables(smarts), ChemInfEngine.CDK, true);		
		try {
			return smart.match(Various.molecule.convertToFormat(smiles, QSPRConstants.SDFAROM_BASIC_NOH));
		} catch (IOException e) {
			throw new UserFriendlyException(e);
		} catch (Exception e) {
			throw new UserFriendlyException(e);
		}
	}

}
