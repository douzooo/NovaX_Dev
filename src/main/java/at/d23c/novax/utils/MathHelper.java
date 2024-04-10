package at.d23c.novax.utils;

public class MathHelper {
    /**
     * Interpolates between two angles, accounting for wrap-around at 360 degrees.
     * 
     * @param prevAngle The previous angle
     * @param angle The current angle
     * @param partialTicks The interpolation fraction
     * @return The interpolated angle
     */
    public static float interpolateAngle(float prevAngle, float angle, float partialTicks) {
        float delta = angle - prevAngle;
        while (delta < -180.0F) {
            delta += 360.0F;
        }
        while (delta >= 180.0F) {
            delta -= 360.0F;
        }
        return prevAngle + partialTicks * delta;
    }
}