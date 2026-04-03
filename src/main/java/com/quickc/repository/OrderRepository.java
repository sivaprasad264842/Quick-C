package com.quickc.repository;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quickc.model.Order;

/**
 * Repository for persisting Order objects in Firebase Realtime Database. All
 * operations are asynchronous; we wrap the Firebase callbacks in
 * CompletableFuture for easier use in services.
 */
@Repository
public class OrderRepository {

    /**
     * Reference to the "orders" node in Firebase
     */
    private final DatabaseReference ordersRef;

    public OrderRepository() {
        // Initialize reference once at construction
        ordersRef = FirebaseDatabase.getInstance().getReference("orders");
    }

    /**
     * Saves (creates or updates) an Order. If the order has no ID, a new
     * push-key is generated.
     *
     * @param order the Order to save
     */
    public void save(Order order) {
        if (order.getId() == null) {
            // Generate a unique Firebase push key
            order.setId(ordersRef.push().getKey());
        }
        // Asynchronous write – fire-and-forget (Firebase handles retries)
        ordersRef.child(order.getId()).setValueAsync(order);
    }

    /**
     * Finds a single order by its Firebase key.
     *
     * @param orderId the Firebase key
     * @return CompletableFuture that completes with the Order or null
     */
    public CompletableFuture<Order> findById(String orderId) {
        CompletableFuture<Order> future = new CompletableFuture<>();

        ordersRef.child(orderId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Order order = snapshot.getValue(Order.class);
                future.complete(order);   // null if not found
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });

        return future;
    }

    /**
     * Retrieves all orders for a specific user (by userId field).
     *
     * @param userId the Firebase user ID
     * @return CompletableFuture with a list of matching orders
     */
    public CompletableFuture<java.util.List<Order>> findByUserId(String userId) {
        CompletableFuture<java.util.List<Order>> future = new CompletableFuture<>();

        ordersRef.orderByChild("userId").equalTo(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        java.util.List<Order> list = new java.util.ArrayList<>();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            Order order = child.getValue(Order.class);
                            if (order != null) {
                                order.setId(child.getKey()); // ensure ID is set
                                list.add(order);
                            }
                        }
                        future.complete(list);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        future.completeExceptionally(error.toException());
                    }
                });

        return future;
    }
}
