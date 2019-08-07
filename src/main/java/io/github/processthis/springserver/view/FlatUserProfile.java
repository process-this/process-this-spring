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


import io.github.processthis.springserver.model.entity.Sketch;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This interface dictates the methods required to create a userProfile object
 */
public interface FlatUserProfile {

  /**
   * This meothd gets the username of the profile
   */
  String getUsername();

  /**
   * This method gets the UUID of the profile
   */
  UUID getId();

  /**
   * This method gets the date a userprofile was created as a Date
   */
  Date getCreated();

  /**
   * This method gets the date a userProfile was updated as a Date
   */
  Date getUpdated();

  /**
   * This method gets the AuthId (associated with Google Sign-in Auth Id) of a user as a String
   */
  String getAuthId();


  /**
   * This method gets the user Bio as a String
   */
  String getBio();

  /**
   * Gets the URI server location of a userProfile object as http://{path}
   */
  URI getHref();
}
