@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import edu.wgu.activitytracker.entities.DistanceUnit
@import edu.wgu.activitytracker.entities.Activity
@import edu.wgu.activitytracker.dto.UserActivityDto
@import java.util.List
@import gg.jte.support.ForSupport

@param List<DistanceUnit> distanceUnits
@param List<Activity> activities
@param List<UserActivityDto> userActivities
@param UserActivityDto userActivity
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <div class="card m-4">
      <div class="card-body">
        Activities
        <table class="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Start</th>
              <th scope="col">End</th>
              <th scope="col">Duration</th>
              <th scope="col">Distance</th>
              <th scope="col">Distance Unit</th>
              <th scope="col">Note</th>
            </tr>
          </thead>
          <tbody>
            @for(var userActivityLoop : ForSupport.of(userActivities))
              <tr>
                <th scope="row">${userActivityLoop.getIndex() + 1}</th>
                <td>${userActivityLoop.get().getActivityName()}</td>
                <td>${userActivityLoop.get().getFormattedStartDateTime()}</td>
                <td>${userActivityLoop.get().getFormattedEndDateTime()}</td>
                <td>${userActivityLoop.get().getFormattedDuration()}</td>
                <td>${userActivityLoop.get().getDistance()}</td>
                <td>${userActivityLoop.get().getDistanceUnitName()}</td>
                <td>${userActivityLoop.get().getNote()}</td>
                <td><a href="#">edit</a></td>
                <td><a href="#">delete</a></td>
              </tr>
            @else
              <tr>
                <td colspan="8">No activities found</td>
              </tr>
            @endfor
          </tbody>
        </table>
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addActivityModal">New Activity</button>
        <a class="btn btn-primary" href="/distance-units">Distance Units</a>
        <a class="btn btn-primary" href="/activities">Activities</a>
      </div>
    </div>
    <div class="modal fade" id="addActivityModal" tabindex="-1" aria-labelledby="addActivityModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">Add New Activity</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="/dashboard/activity/new" method="post">
              ${csrfHiddenInput}
              <label for="activities">activity</label>
              <select name="activityName" id="activities" class="form-select">
                @for (Activity activity : activities)
                  <option value="${activity.getName()}">${activity.getName()}</option>
                @endfor
              </select>
              <label for="startDateTime" class="">
                Start:
              </label>
              <input type="datetime-local"
                     class="form-control mb-3"
                     name="startDateTime"
                     id="startDateTime"
                     value="${String.valueOf(userActivity.getStartDateTime())}"
              >
              <label for="endDateTime" class="">
                End:
              </label>
              <input type="datetime-local"
                     class="form-control mb-3"
                     name="endDateTime"
                     id="endDateTime"
                     onchange="validateEndDate()"
                     value="${String.valueOf(userActivity.getEndDateTime())}"
              >
              <label for="distance" class="">
                Distance:
              </label>
              <input type="number"
                     class="form-control mb-3"
                     name="distance"
                     id="distance"
                     value="${userActivity.getDistance()}"
              >
              <label for="distanceUnit" class="">
                Unit:
              </label>
              <select name="distanceUnitName" id="distanceUnit" class="form-select">
                @for (DistanceUnit unit : distanceUnits)
                  <option value="${unit.getName()}">${unit.getName()}</option>
                @endfor
              </select>
              <label for="note" class="">
                Note:
              </label>
              <input type="text"
                     class="form-control mb-3"
                     name="note"
                     placeholder="Note"
                     id="note"
                     value="${userActivity.getNote()}"
              >
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
              <button type="submit" class="btn btn-primary">Save</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', (event) => {
            const startDate = document.getElementById("startDateTime");
            const endDate = document.getElementById("endDateTime");

            function validateEndDate() {
                if (endDate.value && startDate.value && endDate.value < startDate.value) {
                    endDate.setCustomValidity("The end date must be after the start date");
                } else {
                    endDate.setCustomValidity("");
                }
            }

            startDate.addEventListener("change", validateEndDate);
            endDate.addEventListener("change", validateEndDate);
        });
    </script>
`)