/* 
 * ZDASentence.java
 * Copyright (C) 2010 Kimmo Tuukkanen
 * 
 * This file is part of Java Marine API.
 * <http://sourceforge.net/projects/marineapi/>
 * 
 * Java Marine API is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Java Marine API is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java Marine API. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.marineapi.nmea.sentence;

import net.sf.marineapi.nmea.parser.ParseException;

/**
 * Interface for ZDA sentence type. UTC time and date with local time zone
 * offset.
 * <p>
 * Example: <br>
 * <code>$GPZDA,032915,07,08,2004,00,00*4D</code>
 * 
 * @author Kimmo Tuukkanen
 * @version $Revision$
 */
public interface ZDASentence extends TimeSentence, DateSentence {

    /**
     * Get offset to local time zone in hours, from +/- 0 to +/- 13 hours.
     * 
     * @return Time zone offset
     * @throws ParseException
     */
    int getLocalZoneHours();

    /**
     * Get offset to local time zone in minutes, from 0 to +/- 59.
     * 
     * @return minutes integer
     * @throws ParseException
     */
    int getLocalZoneMinutes();

}
