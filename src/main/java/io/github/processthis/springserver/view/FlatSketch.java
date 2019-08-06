package io.github.processthis.springserver.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * This interface dictates the methods required to create a sketch object
 */
public interface FlatSketch {

  /**Gets the UUID sketchId
   * @return
   */
  UUID getId();

  /**Gets the date a sketch was created as a Date
   * @return
   */
  Date getCreated();

  /**Gets the date a sketch was updated as a Date
   * @return
   */
  Date getUpdated();

  /**Gets the String name of a sketch
   * @return
   */
  String getName();

  /**Gets the String description of a sketch
   * @return
   */
  String getSketchDescription();

  /**Gets the URI server location of a sketch object as http://{path}
   * @return
   */
  URI getHref();


}
