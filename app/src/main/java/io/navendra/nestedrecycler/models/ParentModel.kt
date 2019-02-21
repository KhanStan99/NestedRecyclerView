package io.navendra.nestedrecycler.models

data class ParentModel (
        var position: Int,
        var title : String = "",
        var children : List<ChildModel>
)