/*
 * AISMessage05Parser.java
 * Copyright (C) 2015 Lázár József
 *
 * This file is part of Java Marine API.
 * <http://ktuukkan.github.io/marine-api/>
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
package net.sf.marineapi.ais.parser;

import net.sf.marineapi.ais.message.AISMessage05;
import net.sf.marineapi.ais.util.PositioningDevice;
import net.sf.marineapi.ais.util.ShipType;
import net.sf.marineapi.ais.util.Sixbit;

/**
 * AIS Message 5 implementation: Ship Static and Voyage Related Data.
 *
 * <pre>
 * Field  Name                                      Bits    (from, to )
 * ------------------------------------------------------------------------
 *  1	  messageID                               	   6	(   1,   6)
 *  2	  repeatIndicator                         	   2	(   7,   8)
 *  3	  userID                                  	  30	(   9,  38)
 *  4	  aisVersionIndicator                     	   2	(  39,  40)
 *  5	  imoNumber                               	  30	(  41,  70)
 *  6	  callSign                                	  42	(  71, 112)
 *  7	  name                                    	 120	( 113, 232)
 *  8	  typeOfShipAndCargoType                  	   8	( 233, 240)
 *  9	  dimension                               	  30	( 241, 270)
 * 10	  typeOfElectronicPositionFixingDevice    	   4	( 271, 274)
 * 11	  eta month                                    4	( 275, 278)
 * 11	  eta day                                      5	( 279, 283)
 * 11	  eta hour                                 	   5	( 284, 288)
 * 11	  eta minute                                   6	( 289, 294)
 * 12	  maximumPresentStaticDraught             	   8	( 295, 302)
 * 13	  destination                             	 120	( 303, 422)
 * 14	  dte                                     	   1	( 423, 423)
 * 15	  spare                                   	   1	( 424, 424)
 *                                                  ---- +
 *                                               sum 424
 * </pre>
 *
 * @author Lázár József
 */
class AISMessage05Parser extends AISMessageParser implements AISMessage05 {

    private final static String SEPARATOR = "\n\t";
    private static final int AISVERSION = 0;
    private static final int IMONUMBER = 1;
    private static final int CALLSIGN = 2;
    private static final int NAME = 3;
    private static final int TYPEOFSHIPANDCARGO = 4;
    private static final int BOW = 5;
    private static final int STERN = 6;
    private static final int PORT = 7;
    private static final int STARBOARD = 8;
    private static final int TYPEOFEPFD = 9;
    private static final int MONTH = 10;
    private static final int DAY = 11;
    private static final int HOUR = 12;
    private static final int MINUTE = 13;
    private static final int DRAUGHT = 14;
    private static final int DESTINATION = 15;
    private static final int DTE = 16;

    private final static int[] FROM = { 38, 40, 70, 112, 232, 240, 249, 258, 264, 270, 274, 278, 283, 288, 294, 302 };

    private final static int[] TO = { 40, 70, 112, 232, 240, 249, 258, 264, 270, 274, 278, 283, 288, 294, 302, 422, 423 };

    private final int fAISVersion;
    private final int fIMONumber;
    private final String fCallSign;
    private final String fName;
    private final int fShipAndCargoType;
    private final int fBow;
    private final int fStern;
    private final int fPort;
    private final int fStarboard;
    private final int fTypeOfEPFD;
    private final int fETAMinute;
    private final int fETAHour;
    private final int fETADay;
    private final int fETAMonth;
    private final int fMaximumDraught;
    private final String fDestination;
    private final boolean fDte;

    /**
     * Constructor.
     *
     * @param content
     *            Six-bit message content to parse.
     */
    public AISMessage05Parser(final Sixbit content) {
	super(content, 424, 550);
	fAISVersion = content.getInt(FROM[AISVERSION], TO[AISVERSION]);
	fIMONumber = content.getInt(FROM[IMONUMBER], TO[IMONUMBER]);
	fCallSign = content.getString(FROM[CALLSIGN], TO[CALLSIGN]).trim();
	fName = content.getString(FROM[NAME], TO[NAME]).trim();
	fShipAndCargoType = content.getInt(FROM[TYPEOFSHIPANDCARGO], TO[TYPEOFSHIPANDCARGO]);

	fBow = content.getInt(FROM[BOW], TO[BOW]);
	fStern = content.getInt(FROM[STERN], TO[STERN]);
	fPort = content.getInt(FROM[PORT], TO[PORT]);
	fStarboard = content.getInt(FROM[STARBOARD], TO[STARBOARD]);

	fTypeOfEPFD = content.getInt(FROM[TYPEOFEPFD], TO[TYPEOFEPFD]);

	fETAMonth = content.getInt(FROM[MONTH], TO[MONTH]);
	fETADay = content.getInt(FROM[DAY], TO[DAY]);
	fETAHour = content.getInt(FROM[HOUR], TO[HOUR]);
	fETAMinute = content.getInt(FROM[MINUTE], TO[MINUTE]);

	fMaximumDraught = content.getInt(FROM[DRAUGHT], TO[DRAUGHT]);
	fDestination = content.getString(FROM[DESTINATION], TO[DESTINATION]).trim();
	fDte = content.getBoolean(TO[DTE]);
    }

    @Override
    public int getAISVersionIndicator() {
	return fAISVersion;
    }

    @Override
    public int getIMONumber() {
	return fIMONumber;
    }

    @Override
    public String getCallSign() {
	return fCallSign;
    }

    @Override
    public String getName() {
	return fName;
    }

    @Override
    public int getTypeOfShipAndCargoType() {
	return fShipAndCargoType;
    }

    @Override
    public int getBow() {
	return fBow;
    }

    @Override
    public int getStern() {
	return fStern;
    }

    @Override
    public int getPort() {
	return fPort;
    }

    @Override
    public int getStarboard() {
	return fStarboard;
    }

    @Override
    public int getTypeOfEPFD() {
	return fTypeOfEPFD;
    }

    @Override
    public int getETAMonth() {
	return fETAMonth;
    }

    @Override
    public int getETADay() {
	return fETADay;
    }

    @Override
    public int getETAHour() {
	return fETAHour;
    }

    @Override
    public int getETAMinute() {
	return fETAMinute;
    }

    @Override
    public double getMaximumDraught() {
	return fMaximumDraught / 10.0;
    }

    @Override
    public String getDestination() {
	return fDestination;
    }

    @Override
    public boolean isDteReady() {
	return fDte;
    }

    @Override
    public String toString() {
	String result = "\tIMO:       " + Integer.toString(fIMONumber);
	result += SEPARATOR + "Call sign: " + fCallSign;
	result += SEPARATOR + "Name:      " + fName;
	result += SEPARATOR + "Type:      " + ShipType.shipTypeToString(fShipAndCargoType);
	final String dim = "Bow: " + fBow + ", Stern: " + fStern + ", Port: " + fPort + ", Starboard: " + fStarboard + " [m]";
	result += SEPARATOR + "Dim:       " + dim;
	result += SEPARATOR + "ETA:       " + "Month: " + fETAMonth + ", D: " + fETADay + ", H: " + fETAHour + ", M: " + fETAMinute;
	result += SEPARATOR + "Draft:     " + Float.toString(fMaximumDraught / 10f);
	result += SEPARATOR + "EPFD:      " + PositioningDevice.toString(fTypeOfEPFD);
	result += SEPARATOR + "Dest:      " + fDestination;
	result += SEPARATOR + "DTE:       " + fDte;
	return result;
    }
}
