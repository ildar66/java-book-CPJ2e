package chapter_1.section_2;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

/**
 * Code sketch
 */
class WaterTank {

    final float capacity; //Attributes
    float currentVolume = 0.0f; //Invariant (between zero and capacity)
    WaterTank overflow;

    WaterTank(float cap) {
        capacity = cap;
        //...
    }

    //Operations:

    //Preconditions and postconditions:
    //on the effects of operations, such as rules stating that it is
    //impossible to remove water from an empty tank, or to add water to a full tank that is not
    //equipped with an available overflow tank.

    //Protocols:
    //constraining when and how messages (operation requests) are processed. For
    //example, we may impose a rule that at most one addWater or removeWater message
    //is processed at any given time or, alternatively, a rule stating that removeWater messages
    //are allowed in the midst of addWater operations.
    void addWater(float amount) throws OverflowException {
    }

    void removeWater(float amount) throws UnderflowException {
    }
}
