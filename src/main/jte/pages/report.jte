@import edu.wgu.activitytracker.dto.ReportDto
@import edu.wgu.activitytracker.dto.UserActivityDto
@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import java.util.List
@import org.springframework.validation.ObjectError
@import gg.jte.support.ForSupport

@param ReportDto report
@param List<UserActivityDto> activities
@param List<ObjectError> errors
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
  <div class="m-3">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/user-activities">User Activities</a></li>
        <li class="breadcrumb-item active" aria-current="page">Report</li>
      </ol>
    </nav>
    <div class="card m-4 mx-auto" style="width: 50rem;">
      <div class="card-body">
        <table class="table caption-top">
          <caption>Activities Report</caption>
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
          @if (activities !=null && !activities.isEmpty())
            @for(var userActivityLoop : ForSupport.of(activities))
              <tr>
                <th scope="row">${userActivityLoop.getIndex() + 1}</th>
                <td>${userActivityLoop.get().getActivityName()}</td>
                <td>${userActivityLoop.get().getFormattedStartDateTime()}</td>
                <td>${userActivityLoop.get().getFormattedEndDateTime()}</td>
                <td>${userActivityLoop.get().getFormattedDuration()}</td>
                <td>${userActivityLoop.get().getDistance()}</td>
                <td>${userActivityLoop.get().getDistanceUnitName()}</td>
                <td>${userActivityLoop.get().getNote()}</td>
              </tr>
            @endfor
          @else
            <tr>
              <td colspan="8">No activities found</td>
            </tr>
          @endif
          </tbody>
        </table>
          <div>
            <span>Total Activities: ${report.getNumberOfActivities()}</span><br>
            <span>Total Activities Duration: ${report.getFormattedTotalDuration()}</span><br>
            <span>Average Activities Duration: ${report.getFormattedAverageDuration()}</span>
          </div>
          <a class="btn btn-outline-primary mt-3" href="/user-activities">Home</a>
      </div>
      @if (errors != null)
        <div class="alert alert-danger mt-3" role="alert">
          @for (var error : errors)
            <span>${error.getDefaultMessage()}</span><br>
          @endfor
        </div>
      @endif
    </div>
  </div>
`, csrfHiddenInput = csrfHiddenInput)