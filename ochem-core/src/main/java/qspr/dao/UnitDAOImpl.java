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

package qspr.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import qspr.Globals;
import qspr.entities.Unit;
import qspr.entities.UnitCategory;

@SuppressWarnings("unchecked")
public class UnitDAOImpl implements UnitDAO {
	public Unit get(String name, UnitCategory category)
	{
		name = name.trim().toLowerCase();

		List<Unit> uNames = 			
				Globals.session()
				.createCriteria(Unit.class)
				.add(Restrictions.eq("shortName", Unit.shortName(name)))
				.add(Restrictions.eq("category", category))
				.list();

		if (uNames.size() > 0)
			return uNames.get(0);
		else
			return null;
	}

	@Override
	public Unit getUnitById(long unitId) {
		return (Unit) Globals.session().get(Unit.class, unitId);
	}
}
