/*
 *  Copyright (c) 2009, Wrocław University of Technology
 *  All rights reserved.
 *  Redistribution  and use in source  and binary  forms,  with or
 *  without modification,  are permitted provided that the follow-
 *  ing conditions are met:
 *
 *   • Redistributions of source code  must retain the above copy-
 *     right  notice, this list  of conditions and  the  following
 *     disclaimer.
 *   • Redistributions  in binary  form must  reproduce the  above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the  documentation and/or other mate provided
 *     with the distribution.
 *   • Neither  the name of the  Wrocław University of  Technology
 *     nor the names of its contributors may be used to endorse or
 *     promote products derived from this  software without speci-
 *     fic prior  written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRI-
 *  BUTORS "AS IS" AND ANY  EXPRESS OR IMPLIED WARRANTIES, INCLUD-
 *  ING, BUT NOT  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTA-
 *  BILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 *  NO EVENT SHALL THE COPYRIGHT HOLDER OR  CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT,  INCIDENTAL, SPECIAL,  EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCURE-
 *  MENT OF SUBSTITUTE  GOODS OR SERVICES;  LOSS OF USE,  DATA, OR
 *  PROFITS; OR BUSINESS  INTERRUPTION) HOWEVER  CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *  TORT (INCLUDING  NEGLIGENCE  OR  OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSI-
 *  BILITY OF SUCH DAMAGE.
 */
package apw.classifiers;

import apw.core.Attribute;
import apw.core.Nominal;
import apw.core.Numeric;
import apw.core.Sample;
import apw.core.Samples;
import java.io.Serializable;

/**
 *
 * @author Greg Matoga <greg dot matoga at gmail dot com>
 */
public abstract class Classifier implements Serializable {

    public Classifier(Samples s) {
    }
    
    public final Object classifySampleAsObject(Sample s)
    {
    	Attribute classAttribute = s.getSamples().getClassAttribute();
    	if (classAttribute instanceof Numeric) {
	        double[] darr = classifySample(s);
	        return darr[0];
		}
    	if (classAttribute instanceof Nominal) {
			Nominal nominal = (Nominal) classAttribute;
			double[] darr = classifySample(s);
        
        	int best = 0;
        	double vbest = 0.0;
        	for (int i = 0; i < darr.length; i++) {
    			if(vbest<darr[i])
    			{
    				vbest = darr[i];
    				best = i;
    			}
        	}	
        	String[] keys = nominal.getSortedIKeys();
        	return keys[best];
    	}
    	return null;
   	
    }

    /**
     * Trochę późno na javadoca... Jest jak jest, ale jest!!!!!!!!!! :D
     *
     * Ok, zakładamy, że mamy dane klasy:
     * { A, B, C}, oraz klasyfikator uznał, że Sample s należy do klasy B,
     * zwraca on wtedy na przykład:
     * { 0, 1, 0}, lub cokolwiek podobnego, z zaznaczeniem, że największa
     * wartość odpowiada indeksowi przewidywanej klasy.
     * 
     * @param s
     * @return
     */
    public abstract double[] classifySample(Sample s);

    public abstract void addSamples(Samples s) throws UnsupportedOperationException;

    public abstract void addSample(Sample s) throws UnsupportedOperationException;

    public abstract void rebuild();

    public abstract Classifier copy();
}
