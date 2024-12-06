@import edu.wgu.activitytracker.dto.DistanceUnitDto
@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import java.util.List
@import org.springframework.validation.ObjectError

@param DistanceUnitDto distanceUnit
@param CsrfHiddenInput csrfHiddenInput
@param List<ObjectError> errors

@template.layout.default(
content = @`
  <div class="card m-4 mx-auto" style="width: 25rem;">
    <div class="card-body">
      <h1 class="h4">Save Distance Unit</h1>
      <form action="/distance-units/save" method="POST">
        ${csrfHiddenInput}
        <input type="hidden" name="id" value="${distanceUnit.getId()}">
        <label for="name">
          <span style="color: red;">*</span>Name:
        </label>
        <input type="text"
               class="form-control mb-3"
               name="name"
               id="name"
               value="${distanceUnit.getName()}"
               placeholder="Unit Name"
        >
        <button class="btn btn-primary" type="submit">Save</button>
        <a class="btn btn-outline-primary" href="/distance-units">Cancel</a>
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
`, csrfHiddenInput = csrfHiddenInput)