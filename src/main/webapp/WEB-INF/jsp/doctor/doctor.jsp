<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="container carousel" style="padding-top:10px">
		<div class="row">
			<div class="col-md-2">
				<div role="complementary" class="bs-sidebar hidden-print affix-top">
					<div class="bs-sidebar hidden-print affix-top">

						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="index"><i class="icon-home"></i> Dashboard</a></li>
							<li><a href="#"><i class="icon-envelope"></i> Messages <span
									class="badge badge-info">4</span></a></li>
							<li><a href="#"><i class="icon-comment"></i> Comments <span
									class="badge badge-info">10</span></a></li>
							<li><a href="#"><i class="icon-user"></i>
									Members</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="icon-comment"></i> Settings</a></li>
							<li><a href="#"><i class="icon-share"></i> Logout</a></li>
						</ul>

					</div>
				</div>
			</div>
			<div class="col-md-10">
				<div class="btn-group">
					<a href="#" class="btn btn-inverse"><i
						class="icon-fast-backward"></i></a> <a href="#"
						class="btn btn-inverse"><i class="icon-backward"></i></a> <a
						href="#" class="btn btn-inverse"><i class="icon-stop"></i></a> <a
						href="#" class="btn btn-inverse"><i class="icon-play"></i></a> <a
						href="#" class="btn btn-inverse"><i class="icon-pause"></i></a> <a
						href="#" class="btn btn-inverse"><i class="icon-forward"></i></a>
					<a href="#" class="btn btn-inverse"><i
						class="icon-fast-forward"></i></a>
				</div>
			</div>
			<div class="col-md-10">
			    Search: <input id="filter" type="text"/>
				<table class="footable table" data-filter="#filter" data-page-size="4">
					<thead>
						<tr>
							<th data-toggle="true" class="footable-first-column">
								Dictation ID</th>
							<th data-hide="phone,tablet">Dictation Length (hh:mm:ss)</th>
							<th data-hide="phone">Clinic</th>
							<th data-hide="phone">Dictation Date</th>
							<th data-hide="phone,tablet">Upload Date</th>
							<th data-hide="phone">Transcription Status</th>
							<th data-hide="phone">Transcription file ID</th>
							<th data-hide="phone" class="footable-last-column">
								Dictation Activities</th>

						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00001</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc1</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00002</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc2</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00003</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc3</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00004</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc4</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00005</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc5</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00006</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc7</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00007</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc8</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00008</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc9</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00009</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc10</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
						<tr>
							<td class="footable-first-column"><span
								class="footable-toggle"></span>DICDOC00010</td>
							<td><a href="#">15:00</a></td>
							<td>Clinc6</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="78025368997">22 Jun 2013</td>
							<td data-value="2">In Progress</td>
							<td data-value="0">Not Ready</td>
							<td data-value="1" class="footable-last-column">
							    <div class="btn-group">
									<a href="#" class="btn btn-inverse"><i class="icon-play"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-edit-sign"></i></a> 
									<a href="#"	class="btn btn-inverse"><i class="icon-download-alt "></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-comment"></i></a>
									<a href="#"	class="btn btn-inverse"><i class="icon-thumbs-up"></i></a>
									<a href="#" class="btn btn-inverse"><i class="icon-thumbs-down"></i></a>
								</div>
							</td>

						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<ul class="pagination pagination-centered"></ul>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>


	</div>