<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="classes.Fine" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Fine Reports</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        table th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <h2>Fine Reports</h2>
    <form method="POST" action="FineReportServlet">
        <input type="hidden" name="action" value="viewReports">
        <button type="submit">Load Fine Reports</button>
    </form>

    <h2>All Fines</h2>
    <table border="1">
    <thead>
        <tr>
            
            <th>Fine ID</th>
            <th>Transaction ID</th>
            <th>Fine Amount</th>
            <th>Status</th>
            <th>Due Date</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Fine> fineReports = (List<Fine>) request.getAttribute("fineReports");
            if (fineReports != null && !fineReports.isEmpty()) {
                for (Fine report : fineReports) {
        %>
        <tr>
            
            <td><%= report.getFineId() %></td>
            <td><%= report.getTransactionId() %></td>
            <td><%= report.getFineAmount() %></td>
            <td><%= report.getFineStatus() %></td>
            <td><%= report.getDueDate() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No fine reports available.</td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>


    <!-- Fines Collected Table -->
    <h2 id="finesCollectedTitle" class="report-title hidden">Fines Collected</h2>
    <table id="finesCollectedTable" class="report-table hidden">
        <thead>
            <tr>
                
                <th>Fine ID</th>
                <th>Transaction ID</th>
                <th>Amount</th>
                <th>Payment Date</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                List<Fine> finesCollected = (List<Fine>) request.getAttribute("finesCollected");
                if (finesCollected != null && !finesCollected.isEmpty()) {
                    for (Fine report : finesCollected) {
            %>
            <tr>
                
                <td><%= report.getFineId() %></td>
                <td><%= report.getTransactionId() %></td>
                <td><%= report.getFineAmount() %></td>
                <td><%= report.getDueDate() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No fines collected.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <!-- Outstanding Fines Table -->
     <h2 id="outstandingFinesTitle" class="report-title hidden">Outstanding Fines</h2>
    <table id="outstandingFinesTable" class="report-table hidden"> 
        <thead>
            <tr>
                <th>Fine ID</th>
                <th>Transaction ID</th>
                <th>Amount</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                List<Fine> outstandingFines = (List<Fine>) request.getAttribute("outstandingFines");
                if (outstandingFines != null && !outstandingFines.isEmpty()) {
                    for (Fine report : outstandingFines) {
            %>
            <tr>
                <td><%= report.getFineId() %></td>
                <td><%= report.getTransactionId() %></td>
                <td><%= report.getFineAmount() %></td>
                <td><%= report.getFineStatus() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No outstanding fines.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <!-- Overdue Payments Table -->
    <h2 id="overduePaymentsTitle" class="report-title hidden">Overdue Payments</h2>
    <table id="overduePaymentsTable" class="report-table hidden">
        <thead>
            <tr>
                <th>Fine ID</th>
                <th>Transaction ID</th>
                <th>Amount</th>
                <th>Due Date</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                List<Fine> overduePayments = (List<Fine>) request.getAttribute("overduePayments");
                if (overduePayments != null && !overduePayments.isEmpty()) {
                    for (Fine report : overduePayments) {
            %>
            <tr>
                <td><%= report.getFineId() %></td>
                <td><%= report.getTransactionId() %></td>
                <td><%= report.getFineAmount() %></td>
                <td><%= report.getDueDate() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No overdue payments.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>