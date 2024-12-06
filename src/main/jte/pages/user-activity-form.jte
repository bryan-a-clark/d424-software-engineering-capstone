@import edu.wgu.activitytracker.dto.UserActivityDto
@import edu.wgu.activitytracker.dto.DistanceUnitDto
@import edu.wgu.activitytracker.dto.ActivityDto
@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import java.util.List
@import org.springframework.validation.ObjectError

@param List<DistanceUnitDto> distanceUnits
@param List<ActivityDto> activities
@param UserActivityDto userActivity
@param CsrfHiddenInput csrfHiddenInput
@param List<ObjectError> errors

@template.layout.default(
content = @`
  <div class="card m-4 mx-auto" style="width: 25rem;">
    <div class="card-body">
      <h1 class="h4">Save Activity</h1>
      <form action="/user-activities/user-activity/save" method="POST">
        ${csrfHiddenInput}
        <input type="hidden" name="id" value="${userActivity.getId()}">
        <label for="activities">Activity:</label>
        <select name="activityName" id="activities" class="form-select">
          @for (ActivityDto activity : activities)
            <option value="${activity.getName()}" selected="${userActivity.getActivityName() != null && userActivity.getActivityName().equals(activity.getName())}">${activity.getName()}</option>
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
          @for (DistanceUnitDto unit : distanceUnits)
            <option value="${unit.getName()}" selected="${userActivity.getDistanceUnitName() != null && userActivity.getDistanceUnitName().equals(unit.getName())}">${unit.getName()}</option>
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
        <a class="btn btn-outline-primary" href="/user-activities">Cancel</a>
        <button type="submit" class="btn btn-primary">Save</button>
      </form>
      @if (errors != null)
        <div class="alert alert-danger mt-3" role="alert">
          @for (var error : errors)
            <span>${error.getDefaultMessage()}</span>
          @endfor
        </div>
      @endif
    </div>
  </div>
`, csrfHiddenInput = csrfHiddenInput)