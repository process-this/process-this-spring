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

package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {

  /**
   * Returns a list of all userProfiles ordered by name
   */
  List<UserProfile> getAllByOrderByUsernameAsc();

  /**
   * Gets a list of all UserProfiles ordered by UUID
   */
  List<UserProfile> getAllByOrderById();

  /**
   * returns a ist of all user profiles whose name contains a given string fragment search term
   *
   * @param profileFragment string fragment search term
   */
  List<UserProfile> getAllByUsernameContainsOrderByUsernameAsc(String profileFragment);


}
