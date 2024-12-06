@import edu.wgu.activitytracker.dto.ReportDto
@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import java.util.List
@import org.springframework.validation.ObjectError

@param ReportDto report
@param List<ObjectError> errors
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
  <div class="m-3">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/user-activities">User Activities</a></li>
        <li class="breadcrumb-item active" aria-current="page">Generate Report</li>
      </ol>
    </nav>
    <div class="card m-4 mx-auto" style="width: 25rem;">
      <div class="card-body">
        <h1 class="h4">Generate Report</h1>
        <form action="/report/generate" method="POST">
          ${csrfHiddenInput}
          <label for="startDate">
            <span style="color: red;">*</span>Activities Start Date:
          </label>
          <input type="date"
                 class="form-control mb-3"
                 name="startDate"
                 id="startDate"
                 value="${String.valueOf(report.getStartDate())}"
          >
          <label for="endDate">
            <span style="color: red;">*</span>Activities End Date:
          </label>
          <input type="date"
                 class="form-control mb-3"
                 name="endDate"
                 id="endDate"
                 value="${String.valueOf(report.getEndDate())}"
          >
          <button class="btn btn-primary" type="submit">Generate</button>
          <a class="btn btn-outline-primary" href="/user-activities">Cancel</a>
        </form>
        @if (errors != null)
          <div class="alert alert-danger mt-3" role="alert">
            @for (var error : errors)
              <span>${error.getDefaultMessage()}</span><br>
            @endfor
          </div>
        @endif
      </div>
    </div>
  </div>
`, csrfHiddenInput = csrfHiddenInput)