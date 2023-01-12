
package Example;


import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Social {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Social.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
