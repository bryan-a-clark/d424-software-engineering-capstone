@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import java.util.List
@import edu.wgu.activitytracker.dto.DistanceUnitDto
@import gg.jte.support.ForSupport

@param List<DistanceUnitDto> distanceUnits
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
<div class="m-3">
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/user-activities">User Activities</a></li>
      <li class="breadcrumb-item active" aria-current="page">Distance Units</li>
    </ol>
  </nav>
</div>
<div class="card m-4 mx-auto" style="width: 35rem;">
  <div class="card-body">
    <table class="table caption-top">
      <caption>Distance Units</caption>
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col" class="text-center">Action</th>
      </tr>
      </thead>
      <tbody>
      @for(var distanceUnitLoop : ForSupport.of(distanceUnits))
        <tr>
          <th scope="row">${distanceUnitLoop.getIndex() + 1}</th>
          <td>${distanceUnitLoop.get().getName()}</td>
          @if (!distanceUnitLoop.get().isDefault())
            <td class="text-center">
              <a href="/distance-units/update/${distanceUnitLoop.get().getId()}" class="btn btn-primary btn-sm">Update</a>
              <a href="/distance-units/delete/${distanceUnitLoop.get().getId()}" class="btn btn-danger btn-sm">Delete</a>
            </td>
          @else
            <td class="text-center">
              <button class="btn btn-sm btn-outline-secondary" disabled>Default</button>
            </td>
          @endif
        </tr>
      @else
        <tr>
          <td colspan="8">No distance units found</td>
        </tr>
      @endfor
      </tbody>
    </table>
    <a class="btn btn-primary" href="/distance-units/add">New Distance Unit</a>
  </div>
</div>
`, csrfHiddenInput = csrfHiddenInput)