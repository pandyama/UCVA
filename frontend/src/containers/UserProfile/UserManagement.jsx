import React, { useState } from "react";
import AddUser from "./AddUser";
import BlockUser from "./BlockUser";
import "bulma/css/bulma.min.css";

function UserMgt() {
    const [selection, setSelection] = useState("");
    return (
        <div class="container is-centered">
            <div class="columns is-multiline is-centered">
                <div class="column has-text-centered is-12">
                    <button
                        class="button is-link"
                        onClick={() => {
                            setSelection("add");
                        }}
                    >
                        Add User
                    </button>
                    <button
                        class="button is-danger"
                        onClick={() => {
                            setSelection("block");
                        }}
                    >
                        Block User
                    </button>
                </div>
                {selection === "add" && <AddUser />}
                {selection === "block" && <BlockUser />}
            </div>
        </div>
    );
}

export default UserMgt;
