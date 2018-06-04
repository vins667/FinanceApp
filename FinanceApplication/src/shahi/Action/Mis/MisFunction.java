
package shahi.Action.Mis;

/**
 *
 * @author Ranjeet Gautam
 */
public class MisFunction {

public static double roundToDecimals(double d, int c) {
double temp=(double)((d*Math.pow(10,c)));
  temp=Math.round(temp);
return (((double)temp)/Math.pow(10,c));
}

}
