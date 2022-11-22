## Naming convention
1. Xài tiếng Anh
2. Tên biến viết hoa đầu mỗi từ từ vị trí thứ 2 (vd: **String dayLaMotBien**)
3. Tên ID của layout sử dụng 2 chữ cái đầu của mỗi từ trong tên thẻ (vd: **EditText @+id/something -> etSomething)

## Firebase controller
Có 2 loại là Asynchronous (không block thread chính) và Synchronous (block thread chính)

Vì mục đích dễ hiểu hơn thì xài những hàm với từ khoá Sync ở đuôi

```java
package com.firstapp.duan1.firebase.controller;

import com.firstapp.duan1.firebase.Firebase;
import com.firstapp.duan1.model.Recipe;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerHoaDon extends ControllerBase<Recipe> {
    public ControllerHoaDon() {
        super("table_hoa_don"); // Đặt tên cho "table" mình muốn lưu data
    }

    // Hàm đặt value dưới dạng sync / block thread
    // - value là object mình muốn thêm vào database
    // - update là boolean mình muốn cập nhật lại value ở vị trí <ID> hay chèn mới
    @Override
    public boolean setSync(Recipe value, boolean update) {
        // Trỏ vào vị trí của "table" trên trong firebase
        // Ví dụ:
        // Firebase: {                                  <-- "database" là đây
        //      "table_hoa_don": [                      <-- "tableReference" là đây
        //          rowReference...                     <-- "rowReference" là đây
        //          "xLG029S": {                        <-- Ví dụ một object HoaDon được lưu trên database
        //              "__id": "xLG029S",
        //              "recipeUserId": "kgZ24P",
        //              ...
        //           },
        //           <danh sách biến mình muốn thêm>
        //      ]
        // }
        DatabaseReference tableReference = Firebase.database.child(this.table);
        // Con trỏ chỉ vào vị trí của biến muốn thay đổi
        DatabaseReference rowReference; 

        try {
            // Thêm mới nếu false
            if (!update) {
                // Chèn vào 1 vị trí mới rồi set con trỏ trên
                rowReference = tableReference.push(); 

                // thay đổi <ID> của object bằng ID của con trỏ trên khi chèn mới
                value.__id = rowReference.getKey();

                // Thêm vào database rồi chờ kết quả từ google
                Tasks.await( // Chờ kết quả
                        // Thêm vào
                        Firebase.database.child(this.table).child(Objects.requireNonNull(rowReference.getKey())).setValue(value)
                );
            } else {
                // Cập nhật lại tại vị trí <ID> nếu true
                rowReference = tableReference.child(value.__id);
                Tasks.await(rowReference.setValue(value));
            }

            // Trả về trạng thái thành công
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
            // Trả về trạng thái thất bại
            return false;
        }
    }

    @Override
    public void setAsync(Recipe value, boolean update, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
        // Vì không ai quen với Async và Sync nên bỏ qua những hàm với đuôi Async
    }

    // Để loại bỏ một value tại vị trí <ID>
    // Thì set value tại vị trí đó là "null"
    @Override
    public boolean removeSync(String id) {
        try {
            Tasks.await(Firebase.database.child(this.table).child(id).setValue(null));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAsync(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    // Để lấy một biến từ database thì ta lấy
    // biến tại vị trí <ID> rồi getValue() với class model tương ứng với biến đó
    @Override
    public Recipe getSync(String id) {
        try {
            return Tasks.await(
                    Firebase.database.child(this.table).child(id).get() // Lấy data tại vị trí <ID>
            ).getValue(Recipe.class); // Lấy kết quả dưới dạng model HoaDon
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi hoặc không tìm thấy
        }
    }

    @Override
    public void getAsync(String id, SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }

    // Tương tự như getSync()
    // Lấy tất cả biến từ "table" rồi tạo vòng lặp chạy qua các biến
    // để lấy value dưới dạng model HoaDon rồi thêm vào 1 List
    // kết quả trả về List đó
    @Override
    public List<Recipe> getAllSync() {
        try {
            List<Recipe> list = new ArrayList<>(); // Tạo 1 list trống
                                                         // Lấy tất cả biến con tại vị trí "table"                               
            for (DataSnapshot dataSnapshot : Tasks.await(Firebase.database.child(this.table).get()).getChildren()) {
                // Lấy kết quả tại vị trí con theo model HoaDon rồi thêm vào danh sách
                list.add(dataSnapshot.getValue(Recipe.class));
            }

            // Trả về danh sách các biến
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void getAllAsync(SuccessListener successListener, FailureListener failureListener) {
        // Asynchronous function
    }
}
```
