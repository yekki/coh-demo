/*
 * File: Price.java
 *
 * Copyright (c) 2015, 2016 Oracle and/or its affiliates.
 *
 * You may not use this file except in compliance with the Universal Permissive
 * License (UPL), Version 1.0 (the "License.")
 *
 * You may obtain a copy of the License at https://opensource.org/licenses/UPL.
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */

package com.oracle.coherence.demo.model;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An immutable class to represent a price of a given stock in a financial market for an equity (stock).
 *
 * @author Brian Oliver
 * @author Tim Middleton
 */
@XmlRootElement(name = "price")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Price implements PortableObject {
    private static final long serialVersionUID = -2557678549268609664L;

    /**
     * POF index for symbol attribute.
     */
    private static final int SYMBOL = 0;

    /**
     * POF index for price attribute.
     */
    private static final int PRICE = 1;

    /**
     * The symbol (ticker code) of the equity for the {@link Price}
     */
    private String symbol;

    /**
     * The price of the symbol.
     */
    private double price;


    /**
     * Default Constructor (required and used only by {@link PortableObject}).
     */
    public Price() {
        // required for Serializable and PortableObject
    }


    /**
     * The standard constructor for a {@link Price}.
     *
     * @param symbol The symbol (ticker code) of the {@link Trade}
     * @param price  The current price of the symbol
     */
    public Price(String symbol,
                 double price) {
        this.symbol = symbol;
        this.price = price;
    }


    /**
     * Obtain the symbol (ticker code) of the equity (stock) for the {@link Trade}.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }


    /**
     * Obtain the value at which the shares were acquired for the {@link Trade}.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }


    /**
     * Set the price of the Position.
     *
     * @param price the new price.
     */
    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public void readExternal(PofReader reader) throws IOException {
        symbol = reader.readString(SYMBOL);
        price = reader.readDouble(PRICE);
    }


    @Override
    public void writeExternal(PofWriter writer) throws IOException {
        writer.writeString(SYMBOL, symbol);
        writer.writeDouble(PRICE, price);
    }
}
