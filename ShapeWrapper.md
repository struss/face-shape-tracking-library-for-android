# Introduction #
  * - This class is related to face shape tracking.

  * <b> Usage of creating object </b>
    * - ShapeWrapper sWrapper = ShapeWrapper.getInstance();

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages


# Public Method #
**ShapeWrapper com.jeikei.facelibrary.ShapeWrapper.getInstance()**
  * Return its own class.
  * Since it is only class, it is possible to be called at anywhere and has same data of the shape.

**void com.jeikei.facelibrary.ShapeWrapper.initialise(int _frameWidth, int_frameHeight)**

  * Parameters:
    * frameWidth
    * frameHeight

Warning This method would not need to be called.


**void com.jeikei.facelibrary.ShapeWrapper.release()**
  * Note
    * This method needs to be called on onPause()
    * Otherwise, threads would keep running after application is closed.

**void com.jeikei.facelibrary.ShapeWrapper.resume()**
  * Note
    * This method needs to be called on onPause()
    * Otherwise, threads would keep running after application is closed.

_void com.jeikei.facelibrary.ShapeWrapper.putShape(double[.md](.md)[.md](.md) **shape)**
  * Note
    * This method would not need to be called._

  * Parameters:
    * _shape - The original shape from Active Shape Model.
    *_shape[0](0.md) : X coordinates.
    * _shape[1](1.md) : Y coordinates._


**int com.jeikei.facelibrary.ShapeWrapper.putShape(int x\_or\_y, int idx, double value)**

  * Note
    * his method would not need to be called.

  * Parameters:
    * x\_or\_y
      * - 0 : put x coordinate.
      * - 1 : put y coordinate.

  * idx : index of the coordinates.
  * value : The value.

**double[.md](.md)[.md](.md) com.jeikei.facelibrary.ShapeWrapper.getShape()**
  * Returns:
    * Currently, it will return double[2](2.md)[76](76.md).
    * shape[0](0.md)[x](x.md) : x coordinates.
    * shape[1](1.md)[x](x.md) : y coordinates.


**double com.jeikei.facelibrary.ShapeWrapper.getShape(int x\_or\_y, int idx)**
  * Parameters:
    * x\_or\_y
      * 0 : x coordinate
      * 1 : y coordinate

  * idx : Index number of the coordinate.

  * Returns:
    * -1 : When input parameter is wrong.
    * Otherwise it returns proper value.



**double[.md](.md) com.jeikei.facelibrary.ShapeWrapper.getNormalizedShapeX()**
  * Returns:
    * Normalized array of X coordinates.

  * Its shape is symmetric, size normalized( scope is 0~1 ) and non-rotated.



**double[.md](.md) com.jeikei.facelibrary.ShapeWrapper.getNormalizedShapeY()**
  * Returns:
    * Normalized array of Y coordinates.

  * Its shape is symmetric, size normalized( scope is 0~1 ) and non-rotated.



**float[.md](.md) com.jeikei.facelibrary.ShapeWrapper.getNormalizedShapeX(int fullSize, int locationX)**
  * Parameters:
    * fullSize
      * - Maximum size of the face.
    * locationX
      * - The location of the coordinates that the face shape will be placed.

  * Returns:
    * Its shape is symmetric, size normalized( scope is 0~1 ) and non-rotated.
    * float[76](76.md)



**float[.md](.md) com.jeikei.facelibrary.ShapeWrapper.getNormalizedShapeY(int fullSize, int locationX)**
  * Parameters:
    * fullSize
      * - Maximum size of the face.
    * locationY
      * - The location of the coordinates that the face shape will be placed.

  * locationX

  * Returns:
    * Its shape is symmetric, size normalized( scope is 0~1 ) and non-rotated.
    * float[76](76.md)



**double com.jeikei.facelibrary.ShapeWrapper.getFaceRelativeLocationX()**
  * Returns:
    * Return the relative axis of x. return range is [-1 ~ 1]



**double com.jeikei.facelibrary.ShapeWrapper.getFaceRelativeLocationY()**
  * Returns:
    * Return the relative axis of y. return range is [-1 ~ 1]



**double com.jeikei.facelibrary.ShapeWrapper.getFaceDistance()**
  * Returns:
    * Return the size of the face. return range is [~ 1](0.md)



**double com.jeikei.facelibrary.ShapeWrapper.getEstimatedFaceLocationX()**
  * Returns:
    * no longer used.



**double com.jeikei.facelibrary.ShapeWrapper.getEstimatedFaceLocationY()**
  * Returns:
    * no longer used.



**double com.jeikei.facelibrary.ShapeWrapper.getEstimatedFaceDistance()**
  * Returns:
    * no longer used.



**double com.jeikei.facelibrary.ShapeWrapper.getTimeGap()**
  * Returns:
    * Time gap between two shapes of a face are tracked.

