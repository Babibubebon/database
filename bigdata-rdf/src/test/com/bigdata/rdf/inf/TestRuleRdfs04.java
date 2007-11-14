/**

Copyright (C) SYSTAP, LLC 2006-2007.  All rights reserved.

Contact:
     SYSTAP, LLC
     4501 Tower Road
     Greensboro, NC 27410
     licenses@bigdata.com

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
/*
 * Created on Oct 26, 2007
 */

package com.bigdata.rdf.inf;

import java.util.Properties;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.vocabulary.RDF;
import org.openrdf.vocabulary.RDFS;

import com.bigdata.rdf.inf.InferenceEngine.Options;
import com.bigdata.rdf.spo.ISPOFilter;
import com.bigdata.rdf.store.AbstractTripleStore;

/**
 * Test suite for {@link RuleRdfs04a} and {@link RuleRdfs04b}
 * 
 * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
 * @version $Id$
 */
public class TestRuleRdfs04 extends AbstractRuleTestCase {

    /**
     * 
     */
    public TestRuleRdfs04() {
        super();
    }

    /**
     * @param name
     */
    public TestRuleRdfs04(String name) {
        super(name);
    }

    /**
     * Extended to explicitly turn on
     * {@link Options#FORWARD_CHAIN_RDF_TYPE_RDFS_RESOURCE} for testing
     * {@link RuleRdfs04}.
     */
    public Properties getProperties() {

        Properties properties = new Properties(super.getProperties());
    
        properties.setProperty(Options.FORWARD_CHAIN_RDF_TYPE_RDFS_RESOURCE, "true");
        
        return properties;
        
    }
    
    /**
     * Test of the basic semantics.
     */
    public void test_rdfs4a() {
        
        AbstractTripleStore store = getStore();

        try {

            URI U = new URIImpl("http://www.foo.org/U");
            URI A = new URIImpl("http://www.foo.org/A");
            URI X = new URIImpl("http://www.foo.org/X");
            URI rdfType = new URIImpl(RDF.TYPE);
            URI rdfsResource = new URIImpl(RDFS.RESOURCE);

            store.addStatement(U, A, X);

            assertTrue(store.hasStatement(U, A, X));
            assertEquals(1,store.getStatementCount());

            RDFSHelper vocab = new RDFSHelper(store);
            
            Rule r = new RuleRdfs04a(vocab);
            
            applyRule(store, r, 1/* numComputed */);

            /*
             * validate the state of the primary store.
             * 
             * Note: There is no entailment for (A rdf:type rdfsResource) since
             * it was not used in either a subject or object position.
             */

            assertTrue(store.hasStatement(U, A, X));
            assertTrue(store.hasStatement(U, rdfType, rdfsResource));
            assertFalse(store.hasStatement(X, rdfType, rdfsResource));
            assertEquals(2,store.getStatementCount());

        } finally {

            store.closeAndDelete();

        }

    }
    
    /**
     * Test of the basic semantics.
     */
    public void test_rdfs4b() {
        
        AbstractTripleStore store = getStore();

        try {

            URI U = new URIImpl("http://www.foo.org/U");
            URI A = new URIImpl("http://www.foo.org/A");
            URI V = new URIImpl("http://www.foo.org/V");
            URI rdfType = new URIImpl(RDF.TYPE);
            URI rdfsResource = new URIImpl(RDFS.RESOURCE);

            store.addStatement(U, A, V);

            assertTrue(store.hasStatement(U, A, V));
            assertEquals(1,store.getStatementCount());

            Rule r = new RuleRdfs04b(new RDFSHelper(store));
            
            applyRule(store, r, 1/* numComputed */);

            /*
             * validate the state of the primary store.
             * 
             * Note: There is no entailment for (A rdf:type rdfsResource) since
             * it was not used in either a subject or object position.
             */
            
            assertTrue(store.hasStatement(U, A, V));
            assertFalse(store.hasStatement(U, rdfType, rdfsResource));
            assertTrue(store.hasStatement(V, rdfType, rdfsResource));
            assertEquals(2,store.getStatementCount());

        } finally {

            store.closeAndDelete();

        }

    }
    
    /**
     * Literals may not appear in the subject position, but an rdfs4b entailment
     * can put them there unless you explicitly filter it out.
     * <P>
     * Note: {@link RuleRdfs03} is the other way that literals can be entailed
     * into the subject position.
     */
    public void test_rdfs4b_filterLiterals() {
        
        AbstractTripleStore store = getStore();

        try {

            URI A = new URIImpl("http://www.foo.org/A");
            Literal C = new LiteralImpl("C");
            URI rdfType = new URIImpl(RDF.TYPE);
            URI rdfsResource = new URIImpl(RDFS.RESOURCE);

            store.addStatement(A, rdfType, C);

            assertTrue(store.hasStatement(A, rdfType, C));
            assertEquals(1,store.getStatementCount());

            /*
             * Note: The rule computes the entailment but it gets whacked by the
             * DoNotAddFilter on the InferenceEngine, so it is counted here but
             * does not show in the database.
             */
            
            RDFSHelper vocab = new RDFSHelper(store);
            
            Rule r = new RuleRdfs04b(vocab);
            
            ISPOFilter filter = new DoNotAddFilter(vocab, new NoAxioms(store),
                    true/* forwardChainRdfTypeRdfsResource */);
            
            applyRule(store, r, filter, false /*justified*/, 1/* numComputed */);

            /*
             * validate the state of the primary store - there is no entailment
             * for (A rdf:type rdfs:Resource) since that would allow a literal
             * into the subject position. 
             */
            
            assertTrue(store.hasStatement(A, rdfType, C));
            assertFalse(store.hasStatement(A, rdfType, rdfsResource));
            assertEquals(1,store.getStatementCount());

        } finally {

            store.closeAndDelete();

        }

    }

}
