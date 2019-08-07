/*  Copyright [2019] [Asher Bearce, Jeffery Franken, Matthew Jones, Jennifer Nevares-Diaz]
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
   limitations under the License.
*/

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
