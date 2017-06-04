package io.github.mistercavespider.lina.old;

/**
 * Only used if the point isn't a point.
 * This happens when e.g. points  1, 3
 * and 4 are given. 2 is lost and the LineString 
 * throws this Exception.
 *
 * @author MisterCavespider
 */
@Deprecated
public class UnavailiblePointException extends Exception {
    
    
    
}
