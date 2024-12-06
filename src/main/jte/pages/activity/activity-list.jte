@import java.util.List
@import edu.wgu.activitytracker.dto.ActivityDto
@import gg.jte.support.ForSupport
@import edu.wgu.activitytracker.utils.CsrfHiddenInput

@param CsrfHiddenInput csrfHiddenInput
@param List<ActivityDto> activities

@template.layout.default(
content = @`
  <div class="m-3">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/user-activities">User Activities</a></li>
        <li class="breadcrumb-item active" aria-current="page">Activities</li>
      </ol>
    </nav>
  </div>
  <div class="card m-4 mx-auto" style="width: 35rem;">
    <div class="card-body">
      <table class="table caption-top">
        <caption>Activities</caption>
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col" class="text-center">Action</th>
        </tr>
        </thead>
        <tbody>
        @for(var activitiesLoop : ForSupport.of(activities))
          <tr>
            <th scope="row">${activitiesLoop.getIndex() + 1}</th>
            <td>${activitiesLoop.get().getName()}</td>
            @if (!activitiesLoop.get().isDefault())
              <td class="text-center">
                <a href="/activities/update/${activitiesLoop.get().getId()}" class="btn btn-primary btn-sm">Update</a>
                <a href="/activities/delete/${activitiesLoop.get().getId()}" class="btn btn-danger btn-sm">Delete</a>
              </td>
            @else
              <td class="text-center">
                <button class="btn btn-sm btn-outline-secondary" disabled>Default</button>
              </td>
            @endif
          </tr>
        @else
          <tr>
            <td colspan="8">No activities found</td>
          </tr>
        @endfor
        </tbody>
      </table>
      <a class="btn btn-primary" href="/activities/add">New Activity</a>
    </div>
  </div>
`, csrfHiddenInput = csrfHiddenInput)
