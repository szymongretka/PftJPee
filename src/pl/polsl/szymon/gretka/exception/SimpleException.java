/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.szymon.gretka.exception;

/**
 *
 * @author Szymek
 */
public class SimpleException extends Exception{
    /** Constructor
     *	@param message display message
     */
    public SimpleException(String message){
        super(message);
    }
    /** Constructor without params */
    public SimpleException(){}
}