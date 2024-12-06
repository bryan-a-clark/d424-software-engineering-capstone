@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import edu.wgu.activitytracker.dto.UserActivityDto
@import java.util.List
@import gg.jte.support.ForSupport

@param List<UserActivityDto> userActivities
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <div class="card m-4">
      <div class="card-body">
        <input type="text" id="search-input" onkeyup="searchActivities()" placeholder="Search for Activities by name.." style="width: 25%;">
        <table id="activities-table" class="table caption-top">
          <caption>User Activities</caption>
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
              <th scope="col">Action</th>
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
                <td class="text-center">
                  <a href="/user-activities/update/${userActivityLoop.get().getId()}" class="btn btn-primary btn-sm">Update</a>
                  <a href="/user-activities/delete/${userActivityLoop.get().getId()}" class="btn btn-danger btn-sm">Delete</a>
                </td>
              </tr>
            @else
              <tr>
                <td colspan="8">No activities found</td>
              </tr>
            @endfor
          </tbody>
        </table>
        <a class="btn btn-primary" href="/user-activities/add">New User Activity</a>
        <a class="btn btn-primary" href="/distance-units">Distance Units</a>
        <a class="btn btn-primary" href="/activities">Activities</a>
      </div>
    </div>
  <script>
      function searchActivities() {
          let input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("search-input");
          filter = input.value.toUpperCase();
          table = document.getElementById("activities-table");
          tr = table.getElementsByTagName("tr");

          for (i = 0; i < tr.length; i++) {
              td = tr[i].getElementsByTagName("td")[0];
              if (td) {
                  txtValue = td.textContent || td.innerText;
                  if (txtValue.toUpperCase().indexOf(filter) > -1) {
                      tr[i].style.display = "";
                  } else {
                      tr[i].style.display = "none";
                  }
              }
          }
      }
  </script>
`, csrfHiddenInput = csrfHiddenInput)