package RayTracing;

/**
 * An implementation of an immutable three-dimentional vector and vector
 * arithmetics.
 */
public class Vector {
    public final double x;
    public final double y;
    public final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Vector clone() {
        return new Vector(this.x, this.y, this.z);
    }

    // Arithmetics

    /**
     * Calculate the (element-wise) sum of two vectors.
     * 
     * @return `this` + `other`.
     */
    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Calculate the (element-wise) multiplication of a vector by a scalar.
     * 
     * @return `scalar` * `this`.
     */
    public Vector mul(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * Calculate the negated vector - `this.add(this.neg())` should be equal to a
     * zero vector.
     * 
     * @return the negated vector of `this`.
     */
    public Vector neg() {
        return this.mul(-1);
    }

    /**
     * Calculate the (element-wise) subtraction of two vectors.
     * 
     * @return `this` - `other`.
     */
    public Vector sub(Vector other) {
        return this.add(other.neg());
    }

    /**
     * Calculate the dot product of two vectors.
     * 
     * @return the dot product of `this` and `other`.
     */
    public double dot(Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Calculate the cross product of two vectors.
     * 
     * @return the cross product of `this` and `other`.
     */
    public Vector cross(Vector other) {
        return new Vector(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
    }

    /**
     * Calculate the square of the euclidian distance of two vectors.
     * 
     * @return the squared euclidian distance from `other`.
     */
    public double squaredDistance(Vector other) {
        Vector difference = this.sub(other);
        return difference.dot(difference);
    }

    /**
     * Calculate the euclidian distance of two vectors.
     * 
     * @return the euclidian distance from `other`.
     */
    public double distance(Vector other) {
        return Math.sqrt(this.squaredDistance(other));
    }

    /**
     * Calculates a coordinate-wise multiplication of two vectors
     * 
     * @return A new vector whose coordinates are a product of this and other's
     *         matching coordinates.
     */
    public Vector pointMult(Vector other) {
        return new Vector(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    /**
     * Generate a vector perpendicular to `this`.
     * 
     * @return A vector `v` that satisfies `dot(this, v)` == 0.
     */
    public Vector getPerp() {
        if (this.x == 0 && this.y == 0) {
            return new Vector(1, 1, 0);
        }
        return new Vector(-this.y, this.x, 0);
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        Vector vector = (Vector) other;
        if (this.squaredDistance(vector) < 0.0000000001) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compare the distances of two vectors from `this`.
     * 
     * @param u The first vector in the comparison.
     * @param v A second vector in the comparison.
     * @return -1 if `u` is closer to `this` then `v`, 0 if the distances are equal,
     *         and 1 otherwise.
     */
    public int compareDistances(Vector u, Vector v) {
        return ((Double) this.squaredDistance(u)).compareTo((Double) this.squaredDistance(v));
    }

    /**
     * Returns a normalized form of the given vector
     */
    public Vector normalize() {
        return this.mul(1 / Math.sqrt(this.dot(this)));
    }

    public Vector reflect(Vector normal) {
        return normal.mul(2 * this.dot(normal)).sub(this);
    }

}
