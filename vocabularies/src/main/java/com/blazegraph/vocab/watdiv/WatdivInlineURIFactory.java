/**

Copyright (C) SYSTAP, LLC 2006-2015.  All rights reserved.

Contact:
     SYSTAP, LLC
     2501 Calvert ST NW #106
     Washington, DC 20008
     licenses@systap.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package com.blazegraph.vocab.watdiv;

import com.bigdata.rdf.internal.InlineURIFactory;
import com.bigdata.rdf.internal.InlineUnsignedIntegerURIHandler;


public class WatdivInlineURIFactory extends InlineURIFactory {
	
	public static final String[] INLINE_INT_URIS = {
		"http://db.uwaterloo.ca/~galuc/wsdbm/AgeGroup",
		"http://db.uwaterloo.ca/~galuc/wsdbm/City",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Country",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Gender",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Genre",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Language",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Offer",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Product",
		"http://db.uwaterloo.ca/~galuc/wsdbm/ProductCategory",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Purchase",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Retailer",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Review",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Role",
		"http://db.uwaterloo.ca/~galuc/wsdbm/SubGenre",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Topic",
		"http://db.uwaterloo.ca/~galuc/wsdbm/User",
		"http://db.uwaterloo.ca/~galuc/wsdbm/Website"
	};
	
	/*
	 * 
 http://db.uwaterloo.ca/~galuc/wsdbm/City
http://db.uwaterloo.ca/~galuc/wsdbm/Offer
http://db.uwaterloo.ca/~galuc/wsdbm/Product
http://db.uwaterloo.ca/~galuc/wsdbm/Purchase
http://db.uwaterloo.ca/~galuc/wsdbm/Retailer
http://db.uwaterloo.ca/~galuc/wsdbm/Review
http://db.uwaterloo.ca/~galuc/wsdbm/SubGenre
http://db.uwaterloo.ca/~galuc/wsdbm/User
http://db.uwaterloo.ca/~galuc/wsdbm/Website
http://db.uwaterloo.ca/~galuc/wsdbm/AgeGroup
http://db.uwaterloo.ca/~galuc/wsdbm/City
http://db.uwaterloo.ca/~galuc/wsdbm/Country
http://db.uwaterloo.ca/~galuc/wsdbm/Gender
http://db.uwaterloo.ca/~galuc/wsdbm/Genre
http://db.uwaterloo.ca/~galuc/wsdbm/Language
http://db.uwaterloo.ca/~galuc/wsdbm/Offer
http://db.uwaterloo.ca/~galuc/wsdbm/Product
http://db.uwaterloo.ca/~galuc/wsdbm/ProductCategory
http://db.uwaterloo.ca/~galuc/wsdbm/Purchase
http://db.uwaterloo.ca/~galuc/wsdbm/Retailer
http://db.uwaterloo.ca/~galuc/wsdbm/Review
http://db.uwaterloo.ca/~galuc/wsdbm/Role
http://db.uwaterloo.ca/~galuc/wsdbm/SubGenre
http://db.uwaterloo.ca/~galuc/wsdbm/Topic
http://db.uwaterloo.ca/~galuc/wsdbm/User
http://db.uwaterloo.ca/~galuc/wsdbm/Website
	 */

	public WatdivInlineURIFactory() {
		super();
		
		for (int i = 0; i < INLINE_INT_URIS.length; i++) {
			addHandler(new InlineUnsignedIntegerURIHandler(INLINE_INT_URIS[i]));
		}
	}

}